package com.virtualvenue.model.basetypes;

import java.util.List;

/**
 * An access restricted part of the model
 */
public interface AccessRestricted {

    /**
     * Roles which are allowed to access this restricted part
     *
     * @return list of allowed roles
     */
    List<String> getAccessibleBy();
}
