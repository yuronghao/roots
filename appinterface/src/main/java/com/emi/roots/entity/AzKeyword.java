package com.emi.roots.entity;

public class AzKeyword {
    private Integer id;

    private String keyword;

    private String token;

    private String addon;

    private Integer aimId;

    private Integer ctime;

    private Integer keywordLength;

    private Byte keywordType;

    private Integer extraInt;

    private Integer requestCount;

    private String extraText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon == null ? null : addon.trim();
    }

    public Integer getAimId() {
        return aimId;
    }

    public void setAimId(Integer aimId) {
        this.aimId = aimId;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Integer getKeywordLength() {
        return keywordLength;
    }

    public void setKeywordLength(Integer keywordLength) {
        this.keywordLength = keywordLength;
    }

    public Byte getKeywordType() {
        return keywordType;
    }

    public void setKeywordType(Byte keywordType) {
        this.keywordType = keywordType;
    }

    public Integer getExtraInt() {
        return extraInt;
    }

    public void setExtraInt(Integer extraInt) {
        this.extraInt = extraInt;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText == null ? null : extraText.trim();
    }
}