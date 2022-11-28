package com.virtualvenue.model.widget;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlType;

/**
 * Different display modes for widgets.
 */
@XmlType
@Schema(enumAsRef = true)
public enum DisplayMode {
    EMBEDDED,
    EMBEDDED_DIALOG,
    OVERLAY_DIALOG,
    NEW_TAB
}
