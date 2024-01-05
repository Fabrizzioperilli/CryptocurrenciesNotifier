package org.ull.dap.app.views;

import org.ull.dap.app.models.entities.Asset;

import java.util.List;

public interface IDashboardView {
    /**
     * Update data.
     *
     * @param assets the assets
     */
    void updateData(List<Asset> assets);

}
