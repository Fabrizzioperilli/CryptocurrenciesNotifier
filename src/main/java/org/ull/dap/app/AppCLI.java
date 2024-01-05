package org.ull.dap.app;

import org.ull.dap.app.controllers.AppController;
import org.ull.dap.app.views.IDataView;
import org.ull.dap.app.views.cli.DataViewCLI;
import org.ull.dap.app.models.notifiers.CryptocurrencyNotifier;

public class AppCLI {
    public static void main(String[] args) {
        CryptocurrencyNotifier model = new CryptocurrencyNotifier();
        IDataView view = new DataViewCLI(model);
        new AppController(model, view);
    }
}