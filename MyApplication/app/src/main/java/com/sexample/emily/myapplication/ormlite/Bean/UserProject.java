package com.sexample.emily.myapplication.ormlite.Bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Emily on 2017/4/26.
 */

    @DatabaseTable(tableName="tb_user_project")
    public class UserProject {
        public final static String USER_ID_FIELD_NAME = "user_id";
        public final static String PROJECT_ID_FIELD_NAME = "project_id";
        //用户项目编号
        @DatabaseField(id=true, canBeNull = false)
        private String UserprojectId;
        //关联用户表
        @DatabaseField(foreign = true,columnName=USER_ID_FIELD_NAME)
        private Login user;
        //关联项目表
        @DatabaseField(foreign = true,columnName=PROJECT_ID_FIELD_NAME)
        private Project project;

        public UserProject(){}

        public UserProject(String id, Login user, Project project) {
            this.UserprojectId = id;
            this.user = user;
            this.project = project;
        }


    }
