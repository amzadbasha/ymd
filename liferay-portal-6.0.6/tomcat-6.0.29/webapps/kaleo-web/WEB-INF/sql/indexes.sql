create index IX_50E9112C on KaleoAction (companyId);
create index IX_F95A622 on KaleoAction (kaleoDefinitionId);
create index IX_B88DF9B1 on KaleoAction (kaleoNodeId, executionType);

create index IX_40B9112F on KaleoDefinition (companyId);
create index IX_408542BA on KaleoDefinition (companyId, active_);
create index IX_76C781AE on KaleoDefinition (companyId, name);
create index IX_4C23F11B on KaleoDefinition (companyId, name, active_);
create index IX_EC14F81A on KaleoDefinition (companyId, name, version);

create index IX_5F2FCD2D on KaleoInstance (companyId);
create index IX_BF5839F8 on KaleoInstance (companyId, kaleoDefinitionName, kaleoDefinitionVersion, completionDate);
create index IX_4DA4D123 on KaleoInstance (kaleoDefinitionId);
create index IX_ACF16238 on KaleoInstance (kaleoDefinitionId, completed);

create index IX_153721BE on KaleoInstanceToken (companyId);
create index IX_4A86923B on KaleoInstanceToken (companyId, parentKaleoInstanceTokenId);
create index IX_360D34D9 on KaleoInstanceToken (companyId, parentKaleoInstanceTokenId, completionDate);
create index IX_7BDB04B4 on KaleoInstanceToken (kaleoDefinitionId);
create index IX_F42AAFF6 on KaleoInstanceToken (kaleoInstanceId);

create index IX_73B5F4DE on KaleoLog (companyId);
create index IX_6C64B7D4 on KaleoLog (kaleoDefinitionId);
create index IX_5BC6AB16 on KaleoLog (kaleoInstanceId);
create index IX_25157F25 on KaleoLog (kaleoInstanceTokenId, kaleoNodeId, type_);
create index IX_470B9FF8 on KaleoLog (kaleoInstanceTokenId, type_);
create index IX_B0CDCA38 on KaleoLog (kaleoTaskInstanceTokenId);

create index IX_C4E9ACE0 on KaleoNode (companyId);
create index IX_F28C443E on KaleoNode (companyId, kaleoDefinitionId);
create index IX_32E94DD6 on KaleoNode (kaleoDefinitionId);

create index IX_38829497 on KaleoNotification (companyId);
create index IX_4B968E8D on KaleoNotification (kaleoDefinitionId);
create index IX_A5C459A6 on KaleoNotification (kaleoNodeId, executionType);

create index IX_2C8C4AF4 on KaleoNotificationRecipient (companyId);
create index IX_AA6697EA on KaleoNotificationRecipient (kaleoDefinitionId);
create index IX_7F4FED02 on KaleoNotificationRecipient (kaleoNotificationId);

create index IX_E1F8B23D on KaleoTask (companyId);
create index IX_3FFA633 on KaleoTask (kaleoDefinitionId);
create index IX_77B3F1A2 on KaleoTask (kaleoNodeId);

create index IX_81225C04 on KaleoTaskAssignment (assigneeClassName, kaleoTaskId);
create index IX_611732B0 on KaleoTaskAssignment (companyId);
create index IX_575C03A6 on KaleoTaskAssignment (kaleoDefinitionId);
create index IX_4DD12F58 on KaleoTaskAssignment (kaleoTaskId);

create index IX_6E3CDA1B on KaleoTaskAssignmentInstance (companyId);
create index IX_C851011 on KaleoTaskAssignmentInstance (kaleoDefinitionId);
create index IX_67A9EE93 on KaleoTaskAssignmentInstance (kaleoInstanceId);
create index IX_D4C2235B on KaleoTaskAssignmentInstance (kaleoTaskInstanceTokenId);

create index IX_997FE723 on KaleoTaskInstanceToken (companyId);
create index IX_608E9519 on KaleoTaskInstanceToken (kaleoDefinitionId);
create index IX_2CE1159B on KaleoTaskInstanceToken (kaleoInstanceId);

create index IX_41D6C6D on KaleoTransition (companyId);
create index IX_479F3063 on KaleoTransition (kaleoDefinitionId);
create index IX_A392DFD2 on KaleoTransition (kaleoNodeId);
create index IX_A38E2194 on KaleoTransition (kaleoNodeId, defaultTransition);
create index IX_85268A11 on KaleoTransition (kaleoNodeId, name);