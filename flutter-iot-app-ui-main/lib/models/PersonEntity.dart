import 'package:json_annotation/json_annotation.dart';

part 'PersonEntity.g.dart';

@JsonSerializable()
class PersonEntity {
  PersonEntity();

  late num personId;
  late String name;
  late num roomId;
  
  factory PersonEntity.fromJson(Map<String,dynamic> json) => _$PersonEntityFromJson(json);
  Map<String, dynamic> toJson() => _$PersonEntityToJson(this);
}
