import 'package:json_annotation/json_annotation.dart';

part 'auth_response.g.dart';

@JsonSerializable()
class Auth_response {
  Auth_response();

  late String role;
  late num roomId;
  late num personId;
  late String room;
  late String livingRoom;
  late String kitchen;
  late String name;
  
  factory Auth_response.fromJson(Map<String,dynamic> json) => _$Auth_responseFromJson(json);
  Map<String, dynamic> toJson() => _$Auth_responseToJson(this);
}
