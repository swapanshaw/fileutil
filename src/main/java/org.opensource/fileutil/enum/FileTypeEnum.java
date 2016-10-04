package org.opensource.fileutil.enums;

public enum FileTypeEnum {
    
    TEXT("txt"),
    CSV("csv"),
    XML("xml"),
    JSON("json"),
    DOCX("docx"),
    XLSX("xlsx");
    
    private String value;
    
    private FileTypeEnum(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}
