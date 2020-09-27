package com.scd.erp.Vo.Person;

import com.scd.erp.utils.common.idcardUtils;

import java.io.Serializable;

public class PersonX  implements Serializable {

        private String age;
        private String sex;
        private String adr;
        private String brith;
        private String constellation;
        private String year;

    public PersonX() {
    }

    public PersonX(String idcard) {
            if (idcard.length()==18) {
                this.age = idcardUtils.getAgeByIdCard(idcard) + "";
                this.sex = idcardUtils.getGenderByIdCard(idcard);
                this.adr = idcardUtils.getProvinceByIdCard(idcard);
                this.brith = idcardUtils.getBirthByIdCard(idcard);
                int years = Integer.parseInt(this.brith.substring(0, 4));
                int mo = Integer.parseInt(this.brith.substring(4, 6));
                int day = Integer.parseInt(this.brith.substring(6, 8));
                this.constellation = idcardUtils.getConstellation(mo, day);
                this.year = idcardUtils.getYear(years);
            }
        }

        public String getAge() {
            return age;
        }

        public String getSex() {
            return sex;
        }

        public String getAdr() {
            return adr;
        }

        public String getBrith() {
            return brith;
        }

        public String getConstellation() {
            return constellation;
        }

        public String getYear() {
            return year;
        }

}
