package com.sexample.emily.myapplication.ormlite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Emily on 2017/4/26.
 */

@DatabaseTable(tableName="tb_project")
public class Project {
    public final static String ID_FIELD_NAME = "project_id";
    //项目编号
    @DatabaseField(id=true, canBeNull = false,columnName =ID_FIELD_NAME )
    private String projectId;
    //项目名
    @DatabaseField
    private String projectName;
    public Project() {}
   public Project(String projectId, String projectName) {
            this.projectId = projectId;
            this.projectName = projectName;
        }

    }