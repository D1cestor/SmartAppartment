import 'package:flutter/cupertino.dart';

class MQTTModel with ChangeNotifier {
  var _message = <String>[];

  List<String> get message => _message;


  void addMessage(String message) {
    _message.add(message);
    notifyListeners();
  }
}