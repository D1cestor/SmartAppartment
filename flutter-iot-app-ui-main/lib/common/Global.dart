import 'package:dio/dio.dart';

class Global{
  static late String role;
  static late String name;
  static late int roomId;
  static late int personId;
  static late String room;
  static late String livingRoom;
  static late String kitchen;
  static Dio dio = Dio();
  static Future<Response> query(String path) async {
    var response = await Global.dio.get(path);
    return response;
  }
}