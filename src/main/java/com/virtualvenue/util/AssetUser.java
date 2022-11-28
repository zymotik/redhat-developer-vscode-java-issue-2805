package com.virtualvenue.util;

import com.virtualvenue.model.basetypes.LocalizedContent;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * An instance that uses an asset.
 */
public interface AssetUser {

    /**
     * @param assetId the id of the asset in question.
     * @return List of uses where this instance uses the given asset id.
     */
    List<String> listAssetUses(String assetId);

    /**
     * Helper function to check if a localized asset field contains a given asset.
     * @param contentList the localized asset field.
     * @param assetId the id of the asset to remove.
     * @return whether the asset is used in any localization.
     */
    static boolean localizedAssetInUse(@Nullable List<LocalizedContent> contentList, String assetId) {
        if (contentList == null) {
            return false;
        }
        return contentList.stream().anyMatch(content -> assetId.equals(content.getAssetId()));
    }

    /**
     * Helper function to use if a component only has a single asset.
     * @param assetId the id of the asset to remove.
     * @param myAssetId the id of the component's asset in question.
     * @param usageString the string to return containing usage information.
     * @return An empty list if the asset ids differ or a singletonList containing the given usage String if they equal
     */
    static List<String> singleAsset(String assetId, @Nullable String myAssetId, String usageString) {
        if (assetId.equals(myAssetId)) {
            return Collections.singletonList(usageString);
        }
        return Collections.emptyList();
    }

    /**
     * Helper function to use if a component only has a single asset.
     * The usageString generator will only be executed if the asset ids equal.
     * Can be used e.g. if the usage string first has to be concatenated.
     * @param assetId the id of the asset to remove.
     * @param myAssetId the id of the component's asset in question.
     * @param usageStringGenerator generates the string to return containing usage information.
     * @return An empty list if the asset ids differ or a singletonList containing the given usage String if they equal
     */
    static List<String> singleAsset(String assetId, @Nullable String myAssetId, Supplier<String> usageStringGenerator) {
        if (assetId.equals(myAssetId)) {
            return Collections.singletonList(usageStringGenerator.get());
        }
        return Collections.emptyList();
    }

}
