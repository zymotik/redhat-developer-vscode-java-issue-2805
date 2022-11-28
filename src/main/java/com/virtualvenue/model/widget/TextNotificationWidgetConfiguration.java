package com.virtualvenue.model.widget;

import com.virtualvenue.model.basetypes.LocalizedText;
import com.virtualvenue.model.basetypes.Asset;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for a TeamsWidget
 */
@Data
@SuperBuilder(toBuilder = true)
//noArgs required for de-serializer and AllArgs for builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlType(propOrder = {})//tell jaxb schemagen to generate xs:all instead of xs:sequence (we dont want ordering)
@XmlAccessorType(XmlAccessType.FIELD)
public class TextNotificationWidgetConfiguration extends ScheduledContentInstance {

    @NonNull
    @Singular
    @XmlElement(name = "localizedText")//element name matches Type and not the field name
    @XmlElementWrapper(name = "title")
    List<LocalizedText> titles = new ArrayList<>();//@NonNull!

    @NonNull
    @Singular
    @XmlElement(name = "localizedText")//element name matches Type and not the field name
    @XmlElementWrapper(name = "content")
    List<LocalizedText> contents = new ArrayList<>();//@NonNull!

    /**
     * Optional: The image assetID to be displayed as background
     * <p>
     * {@link Asset} MUST be from asset list: {@link com.virtualvenue.model.Venue#getAssets()}
     * && {@link Asset#getArchitectureId()} must equal this contents parent architecture id)
     */
    @Nullable
    String backgroundPictureAssetId = "";


    /**
     * Optional: an URL to open on click into the text notification widget content pane
     */
    @Nullable
    String onClickLink = "";

    /**
     * if this popup is closable or not (has an x close button)
     */
    boolean closeable = true;

    @Override
    public boolean assetUsedInConf(String assetId) {
        return assetId.equals(this.backgroundPictureAssetId);
    }
}
