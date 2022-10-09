// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'auth_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Auth_response _$Auth_responseFromJson(Map<String, dynamic> json) =>
    Auth_response()
      ..role = json['role'] as String
      ..roomId = json['roomId'] as num
      ..personId = json['personId'] as num
      ..room = json['room'] as String
      ..livingRoom = json['livingRoom'] as String
      ..kitchen = json['kitchen'] as String
      ..name = json['name'] as String;

Map<String, dynamic> _$Auth_responseToJson(Auth_response instance) =>
    <String, dynamic>{
      'role': instance.role,
      'roomId': instance.roomId,
      'personId': instance.personId,
      'room': instance.room,
      'livingRoom': instance.livingRoom,
      'kitchen': instance.kitchen,
      'name': instance.name,
    };
