package model;

public class AddressModel {
        private String street;
        private String city;
        private String state;
        private String zipcode;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setStreet(String string) {
            this.street = string;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
    }

