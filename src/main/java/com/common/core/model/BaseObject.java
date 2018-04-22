package com.common.core.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.common.core.builder.ToStringBuilder;
import com.common.core.builder.ToStringStyle;

public class BaseObject implements Serializable {
    private static final long serialVersionUID = 4539343756018673380L;

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, new String[0]);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[0]);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
