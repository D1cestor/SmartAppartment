// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'PersonEntity.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonEntity _$PersonEntityFromJson(Map<String, dynamic> json) => PersonEntity()
  ..personId = json['personId'] as num
  ..name = json['name'] as String
  ..roomId = json['roomId'] as num;

Map<String, dynamic> _$PersonEntityToJson(PersonEntity instance) =>
    <String, dynamic>{
      'personId': instance.personId,
      'name': instance.name,
      'roomId': instance.roomId,
    };
