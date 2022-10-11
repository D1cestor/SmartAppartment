// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'PersonalTemperaturePreference.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

PersonalTemperaturePreference _$PersonalTemperaturePreferenceFromJson(
        Map<String, dynamic> json) =>
    PersonalTemperaturePreference()
      ..person = json['person']
      ..temperature = json['temperature'] as num;

Map<String, dynamic> _$PersonalTemperaturePreferenceToJson(
        PersonalTemperaturePreference instance) =>
    <String, dynamic>{
      'person': instance.person,
      'temperature': instance.temperature,
    };
