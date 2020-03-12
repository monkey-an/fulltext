package com.fulltext.project.constants;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/9.
 *
 * @author anlu.
 */
public enum RoleEnum {
    SUPER_ADMIN(1L,"超级管理员",null),
    CONTENT_ADMIN(2L,"内容管理员",null),
    STAFF(6L,"内部员工",null),
    HR(4L,"人事专员",6L),
    AD(5L,"行政专员",6L),
    NORMAL_USER(3L,"普通用户",null);

    public String roleName;
    public Long parentRoleId;
    public Long roleId;

    RoleEnum(Long roleId,String roleName,Long parentRoleId){
        this.roleId = roleId;
        this.roleName = roleName;
        this.parentRoleId = parentRoleId;
    }
}
