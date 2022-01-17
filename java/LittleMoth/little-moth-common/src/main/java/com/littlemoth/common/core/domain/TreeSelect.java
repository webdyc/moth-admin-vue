package com.littlemoth.common.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.littlemoth.common.core.domain.entity.SysDept;
import com.littlemoth.common.core.domain.entity.SysMenu;
import com.littlemoth.common.core.domain.entity.TbSysMenu;

/**
 * Treeselect树结构实体类
 * 
 * @author xiusan
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;
    /** 节点ID */
    private Long value;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(SysDept dept)
    {
        this.id = dept.getDeptId();
        this.value = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(TbSysMenu menu)
    {
        this.id = menu.getId();
        this.value = menu.getId();
        this.label = menu.getName();
        this.children = menu.getChildren().stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    public TreeSelect(SysMenu sysMenu) {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeSelect> children)
    {
        this.children = children;
    }
}
