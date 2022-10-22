import 'dart:async';
import 'dart:io';
import 'package:flutter/material.dart';
import 'package:iot_ui_challenge/model/device_model.dart';
import 'package:iot_ui_challenge/pages/home/widgets/devices.dart';
import 'package:iot_ui_challenge/utils/string_to_color.dart';
import 'package:mqtt_client/mqtt_client.dart';
import 'package:mqtt_client/mqtt_server_client.dart';

import '../../common/Global.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

final client = MqttServerClient('10.0.2.2', Global.roomId.toString());

class _HomePageState extends State<HomePage> {
  List<DeviceModel> livingRoomDevices = [
    DeviceModel(
        name: 'Smart Spotlight',
        isActive: false,
        color: "#ff5f5f",
        icon: 'assets/svg/light.svg'),
    DeviceModel(
        name: 'Smart AC',
        isActive: false,
        color: "#7739ff",
        icon: 'assets/svg/ac.svg'),
    DeviceModel(
        name: 'Smart Window',
        isActive: false,
        color: '#000000',
        icon: 'assets/svg/window.svg'),
  ];
  List<String> messages = [];
  void refresh() {
    Global.query("${Global.livingRoom}/light").then((response) {
      livingRoomDevices[0].isActive = response.data;
      livingRoomDevices[0].link = "${Global.livingRoom}/light";
    });
    Global.query("${Global.livingRoom}/temperature").then((response) {
      livingRoomDevices[1].isActive = response.data;
      livingRoomDevices[1].link = "${Global.livingRoom}/temperature";
    });
    Global.query("${Global.livingRoom}/window").then((response) {
      livingRoomDevices[2].isActive = response.data;
      livingRoomDevices[2].link = "${Global.livingRoom}/window";
      setState(() {});
    });
  }

  @override
  void dispose() {
    super.dispose();
  }

  Future<void> _connect() async {
    /// Set the correct MQTT protocol for mosquito
    client.setProtocolV311();
    client.keepAlivePeriod = 20;
    client.connectTimeoutPeriod = 2000; // milliseconds
    client.onDisconnected = onDisconnected;
    client.onConnected = onConnected;
    client.onSubscribed = onSubscribed;
    final connMess = MqttConnectMessage()
        .withClientIdentifier('Mqtt_MyClientUniqueId')
        .withWillTopic('willtopic') // If you set this you must set a will message
        .withWillMessage('My Will message')
        .startClean() // Non persistent session for testing
        .withWillQos(MqttQos.atLeastOnce);
    print('EXAMPLE::Mosquitto client connecting....');
    client.connectionMessage = connMess;
    try {
      await client.connect();
    } on NoConnectionException catch (e) {
      // Raised by the client when connection fails.
      print('EXAMPLE::client exception - $e');
      client.disconnect();
    } on SocketException catch (e) {
      // Raised by the socket layer
      print('EXAMPLE::socket exception - $e');
      client.disconnect();
    }
    /// Check we are connected
    if (client.connectionStatus!.state == MqttConnectionState.connected) {
      print('EXAMPLE::Mosquitto client connected');
      print('EXAMPLE::Subscribing to the test/lol topic');
      var topic = 'delivery/${Global.roomId}';
      client.subscribe(topic, MqttQos.atMostOnce);
      client.updates!.listen((List<MqttReceivedMessage<MqttMessage?>>? c) {
        final recMess = c![0].payload as MqttPublishMessage;
        final pt =
        MqttPublishPayload.bytesToStringAsString(recMess.payload.message);
        messages.add(pt);
        setState(() {});
        print(
            'EXAMPLE::Change notification:: topic is <${c[0].topic}>, payload is <-- $pt -->');
        print('');
      });
      client.published!.listen((MqttPublishMessage message) {
        print(
            'EXAMPLE::Published notification:: topic is ${message.variableHeader!.topicName}, with Qos ${message.header!.qos}');
      });
      print('EXAMPLE::Sleeping....');
      await MqttUtilities.asyncSleep(60);
      print('EXAMPLE::Disconnecting');
      client.disconnect();
      print('EXAMPLE::Exiting normally');
    } else {
      /// Use status here rather than state if you also want the broker return code.
      print(
          'EXAMPLE::ERROR Mosquitto client connection failed - disconnecting, status is ${client.connectionStatus}');
      client.disconnect();
    }

  }

  /// The subscribed callback
  void onSubscribed(String topic) {
    print('EXAMPLE::Subscription confirmed for topic $topic');
  }

  /// The unsolicited disconnect callback
  void onDisconnected() {
    print('EXAMPLE::OnDisconnected client callback - Client disconnection');
    if (client.connectionStatus!.disconnectionOrigin ==
        MqttDisconnectionOrigin.solicited) {
      print('EXAMPLE::OnDisconnected callback is solicited, this is correct');
    } else {
      print(
          'EXAMPLE::OnDisconnected callback is unsolicited or none, this is incorrect - exiting');
      exit(-1);
    }
  }

  /// The successful connect callback
  void onConnected() {
    print(
        'EXAMPLE::OnConnected client callback - Client connection was successful');
  }

  @override
  void initState() {
    refresh();
    super.initState();
    _connect();
  }


  Widget _buildMessageView(BuildContext context,String messages) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 25.0, vertical: 15.0),
      decoration: BoxDecoration(
        color: Colors.pink[200],
        borderRadius: const BorderRadius.only(
          topRight: Radius.circular(20.0),
          bottomRight: Radius.circular(20.0),
        ),
      ),
      margin: const EdgeInsets.only(bottom: 8.0, right: 80.0),
      child: Text(
        messages,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        width: MediaQuery.of(context).size.width,
        height: MediaQuery.of(context).size.height,
        decoration: const BoxDecoration(
          gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment.bottomRight,
              colors: <Color>[Color(0xFFfce2e1), Colors.white]),
        ),
        child: Padding(
          padding: const EdgeInsets.fromLTRB(20, 15, 20, 0),
          child: SafeArea(
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      ("Hi,${Global.name}"),
                      style: const TextStyle(
                          fontSize: 28,
                          color: Colors.black,
                          fontWeight: FontWeight.bold),
                    ),
                    const CircleAvatar(
                        minRadius: 16,
                        backgroundImage: AssetImage("assets/images/user.webp"))
                  ],
                ),
                const SizedBox(
                  height: 30,
                ),
                Container(
                  height: 300,
                  child: Container(
                    width: MediaQuery.of(context).size.width,
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        topRight: Radius.circular(30.0),
                        topLeft: Radius.circular(30.0),
                      ),
                      color: Colors.white,
                    ),
                    child: Padding(
                      padding: const EdgeInsets.all(20.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const SizedBox(
                            height: 5,
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    "A total of ${livingRoomDevices.length} devices",
                                    style: const TextStyle(
                                        fontSize: 15,
                                        color: Colors.grey,
                                        fontWeight: FontWeight.normal),
                                  ),
                                  const Text(
                                    "Living Room",
                                    style: TextStyle(
                                        height: 1.1,
                                        fontSize: 17,
                                        color: Colors.black,
                                        fontWeight: FontWeight.w600),
                                  ),
                                ],
                              ),
                              Icon(
                                Icons.more_horiz,
                                color: Colors.grey[300],
                                size: 30,
                              )
                            ],
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Expanded(
                            child: GridView.builder(
                                padding:
                                    const EdgeInsets.only(top: 10, bottom: 20),
                                gridDelegate:
                                    const SliverGridDelegateWithMaxCrossAxisExtent(
                                        maxCrossAxisExtent: 200,
                                        childAspectRatio: 3 / 4,
                                        crossAxisSpacing: 20,
                                        mainAxisSpacing: 20),
                                itemCount: livingRoomDevices.length,
                                itemBuilder: (BuildContext ctx, index) {
                                  return Devices(
                                    name: livingRoomDevices[index].name,
                                    svg: livingRoomDevices[index].icon,
                                    color: livingRoomDevices[index]
                                        .color
                                        .toColor(),
                                    isActive: livingRoomDevices[index].isActive,
                                    link: livingRoomDevices[index].link,
                                    onChanged: (val) {
                                      setState(() {
                                        livingRoomDevices[index].isActive =
                                            !livingRoomDevices[index].isActive;
                                        Global.dio.post(
                                            livingRoomDevices[index].link,
                                            data: livingRoomDevices[index]
                                                .isActive
                                                .toString());
                                      });
                                    },
                                  );
                                }),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
                Container(
                  // padding: const EdgeInsets.all(20.0),
                  child: Container(
                    width: MediaQuery.of(context).size.width,
                    decoration: const BoxDecoration(
                      borderRadius: BorderRadius.only(
                        topRight: Radius.circular(30.0),
                        topLeft: Radius.circular(30.0),
                      ),
                      color: Colors.white,
                    ),
                    child: Column(
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const SizedBox(
                              height: 5,
                            ),
                            const SizedBox(
                              height: 10,
                            ),
                            const Text(
                              "Notification",
                              style: TextStyle(
                                  height: 1.1,
                                  fontSize: 17,
                                  color: Colors.black,
                                  fontWeight: FontWeight.w600),
                            ),
                           SizedBox(
                                  height: 300.0,
                                  child: ListView.builder(
                                    padding: const EdgeInsets.only(top: 25.0),
                                    itemCount: messages.length,
                                    itemBuilder: (context, index) =>
                                        _buildMessageView(context, messages[index]),
                                  )
                           ),
                          ],
                        ),

                      ]
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
