package com.uptc.controller;

public enum Commands {
	C_ADD_PROCESS,C_EXECUTE_PROCESS,C_ADD_PARTITION, C_ADD_PROCESS_TO_PARTICION,
	C_REPORT_FOR_STATUS_CHANGE_PROCESS,C_REPORT_BY_EXIT_STATE,
	C_REPORT_BY_READY_STATES,C_REPORT_BY_EXECUTE_STATES,
	C_REPORT_BY_LOCKED_STATES,C_REPORT_FOR_STATUS_CHANGE, C_CLOSE_APP,C_DELETTE_PROCESS,
	C_REPORT_BY_LIST_PARTITION, C_CLOSE_DIALOG_REPORT, C_SAVE_PARTITION, C_CLOSE_DIALOG_ADD_PROCESS,
	C_SAVE_PROCESS_PARTITION;


	private String name;

    private Commands() {
		this.name = "";
	}

    public String getName() {
        return name;
    }

	public void setName(String name){
		this.name=name;
	}
}
