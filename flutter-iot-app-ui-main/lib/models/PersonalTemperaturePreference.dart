import 'package:json_annotation/json_annotation.dart';
import "PersonEntity.dart";
part 'PersonalTemperaturePreference.g.dart';

@JsonSerializable()
class PersonalTemperaturePreference {
  PersonalTemperaturePreference();

  late PersonEntity person;
  late num temperature;
  
  factory PersonalTemperaturePreference.fromJson(Map<String,dynamic> json) => _$PersonalTemperaturePreferenceFromJson(json);
  Map<String, dynamic> toJson() => _$PersonalTemperaturePreferenceToJson(this);
}
