package com.fulltext.project.constants;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */
public enum KeyWordTypeEnum {
    AUTHOR(0),
    TITLE(1),
    BODY(2),
    SUMMARY(3),
    KEYWORD(4),
    MEMBER(5);

    public int type;
    KeyWordTypeEnum(int type){
        this.type = type;
    }
}
