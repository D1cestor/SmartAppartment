import 'package:flutter/material.dart';
import 'package:iot_ui_challenge/model/device_model.dart';
import 'package:iot_ui_challenge/pages/home/widgets/devices.dart';
import 'package:iot_ui_challenge/utils/string_to_color.dart';
import 'package:mqtt_client/mqtt_client.dart';
import 'package:provider/provider.dart';
import '../../common/Global.dart';
import '../../common/MQTTClientManager.dart';
import '../../model/mqt_model.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

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
  late MQTTModel state;
  void refresh() {
    Global.query(Global.livingRoom + "/light").then((response) {
      livingRoomDevices[0].isActive = response.data;
      livingRoomDevices[0].link = Global.livingRoom + "/light";
    });
    Global.query(Global.livingRoom + "/temperature").then((response) {
      livingRoomDevices[1].isActive = response.data;
      livingRoomDevices[1].link = Global.livingRoom + "/temperature";
    });
    Global.query(Global.livingRoom + "/window").then((response) {
      livingRoomDevices[2].isActive = response.data;
      livingRoomDevices[2].link = Global.livingRoom + "/window";
      setState(() {});
    });
  }

  MQTTClientManager mqttClientManager = MQTTClientManager();

  @override
  void dispose() {
    mqttClientManager.disconnect();
    super.dispose();
  }

  @override
  void initState() {
    refresh();
    state = Provider.of<MQTTModel>(context);
    setupMqttClient();
    super.initState();
  }

  Future<void> setupMqttClient() async {
    await mqttClientManager.connect();
    mqttClientManager.subscribe("delivery/" + Global.roomId.toString());
  }

  void setupUpdatesListener() {
    mqttClientManager
        .getMessagesStream()!
        .listen((List<MqttReceivedMessage<MqttMessage?>>? c) {
      final recMess = c![0].payload as MqttPublishMessage;
      final pt =
      MqttPublishPayload.bytesToStringAsString(recMess.payload.message);
      state.addMessage(pt);
      print('MQTTClient::Message received on topic: <${c[0].topic}> is $pt\n');
    });
  }



  Widget _buildMessageView(String messages) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 25.0, vertical: 15.0),
      decoration: BoxDecoration(
        color: Colors.pink[200],
        borderRadius: BorderRadius.only(
          topRight: Radius.circular(20.0),
          bottomRight: Radius.circular(20.0),
        ),
      ),
      margin: EdgeInsets.only(top: 8.0, bottom: 8.0, right: 80.0),
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
                      ("Hi," + Global.name),
                      style: TextStyle(
                          fontSize: 28,
                          color: Colors.black,
                          fontWeight: FontWeight.bold),
                    ),
                    CircleAvatar(
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
                                    "A total of " +
                                        livingRoomDevices.length.toString() +
                                        " devices",
                                    style: TextStyle(
                                        fontSize: 15,
                                        color: Colors.grey,
                                        fontWeight: FontWeight.normal),
                                  ),
                                  Text(
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
                            Text(
                              "Notification",
                              style: TextStyle(
                                  height: 1.1,
                                  fontSize: 17,
                                  color: Colors.black,
                                  fontWeight: FontWeight.w600),
                            ),
                            ListView.builder(
                              padding: EdgeInsets.only(top: 25.0),
                              itemCount: state.message.length,
                              itemBuilder: (context, index) =>
                                  _buildMessageView(state.message[index]),
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
