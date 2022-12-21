package com.example.wheatherapitest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class WeatherModel implements Serializable {
    @Expose
    @SerializedName("response")
    public Response response;

    public static class Response implements Serializable {
        @Expose
        @SerializedName("body")
        private Body body;

        @Expose
        @SerializedName("header")
        private Header header;

        @Override
        public String toString() {
            return "Response{" +
                    "body=" + body +
                    ", header=" + header +
                    '}';
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

    }

    public static class Body implements Serializable {
        @Expose
        @SerializedName("totalCount")
        private int totalCount;

        @Expose
        @SerializedName("numOfRows")
        private int numOfRows;

        @Expose
        @SerializedName("pageNo")
        private int pageNo;

        @Expose
        @SerializedName("items")
        private Items items;

        @Expose
        @SerializedName("dataType")
        private String dataType;

        @Override
        public String toString() {
            return "Body{" +
                    "totalCount=" + totalCount +
                    ", numOfRows=" + numOfRows +
                    ", pageNo=" + pageNo +
                    ", items=" + items +
                    ", dataType='" + dataType + '\'' +
                    '}';
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(int numOfRows) {
            this.numOfRows = numOfRows;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

    }

    public static class Items implements Serializable {
        @Expose
        @SerializedName("item")
        private List<Item> item;

        @Override
        public String toString() {
            return "Items{" +
                    "item=" + item +
                    '}';
        }

        public List<Item> getItem() {
            return item;
        }

        public void setItem(List<Item> item) {
            this.item = item;
        }

    }

    public static class Item implements Serializable {
        @Expose
        @SerializedName("ny")
        private int ny;

        @Expose
        @SerializedName("nx")
        private int nx;

        @Expose
        @SerializedName("fcstValue")
        private String fcstValue;

        @Expose
        @SerializedName("fcstDate")
        private String fcstDate;

        @Expose
        @SerializedName("category")
        private String category;

        @Expose
        @SerializedName("baseTime")
        private String baseTime;

        @Expose
        @SerializedName("baseDate")
        private String baseDate;

        @Expose
        @SerializedName("fcstTime")
        private String fcstTime;

        @Override
        public String toString() {
            return "Item{" +
                    "ny=" + ny +
                    ", nx=" + nx +
                    ", fcstValue='" + fcstValue + '\'' +
                    ", fcstDate='" + fcstDate + '\'' +
                    ", category='" + category + '\'' +
                    ", baseTime='" + baseTime + '\'' +
                    ", baseDate='" + baseDate + '\'' +
                    ", fcstTime='" + fcstTime + '\'' +
                    '}';
        }

        public String getFcstTime() {
            return fcstTime;
        }

        public void setFcstTime(String fcstTime) {
            this.fcstTime = fcstTime;
        }

        public int getNy() {
            return ny;
        }

        public void setNy(int ny) {
            this.ny = ny;
        }

        public int getNx() {
            return nx;
        }

        public void setNx(int nx) {
            this.nx = nx;
        }

        public String getFcstValue() {
            return fcstValue;
        }

        public void setFcstValue(String fcstValue) {
            this.fcstValue = fcstValue;
        }

        public String getFcstDate() {
            return fcstDate;
        }

        public void setFcstDate(String fcstDate) {
            this.fcstDate = fcstDate;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getBaseTime() {
            return baseTime;
        }

        public void setBaseTime(String baseTime) {
            this.baseTime = baseTime;
        }

        public String getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(String baseDate) {
            this.baseDate = baseDate;
        }

    }

    public static class Header implements Serializable {
        @Expose
        @SerializedName("resultMsg")
        private String resultMsg;

        @Expose
        @SerializedName("resultCode")
        private String resultCode;

        @Override
        public String toString() {
            return "Header{" +
                    "resultMsg='" + resultMsg + '\'' +
                    ", resultCode='" + resultCode + '\'' +
                    '}';
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "response=" + response +
                '}';
    }
}
