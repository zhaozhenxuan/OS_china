package com.example.administrator.os_china.model.entity.find_Beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class Fenlei_Beans {

    private String softwarecount;
    private List<SoftwareTypeBean> softwareTypes;

    public String getSoftwarecount() {
        return softwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        this.softwarecount = softwarecount;
    }

    public List<SoftwareTypeBean> getSoftwareTypes() {
        return softwareTypes;
    }

    public void setSoftwareTypes(List<SoftwareTypeBean> softwareTypes) {
        this.softwareTypes = softwareTypes;
    }

    public static class SoftwareTypeBean {
        private String name;
        private String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
