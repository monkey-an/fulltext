package com.fulltext.project.constants;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/7.
 *
 * @author anlu.
 */
public enum ElementTypeEnum {
    BOOK_IMAGE(1),//封面

    PDF_DOWN_LOAD_PATH(2),//文章PDF

    WORD_DOWN_LOAD_PATH(3);//文章WORD

    public int value;

    ElementTypeEnum(int value) {
        this.value = value;
    }
}
