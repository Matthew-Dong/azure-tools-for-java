/*
 * Copyright (c) Microsoft Corporation
 *   <p/>
 *  All rights reserved.
 *   <p/>
 *  MIT License
 *   <p/>
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 *  to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *  <p/>
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 *  the Software.
 *   <p/>
 *  THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 *  THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 *  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.microsoft.azuretools.sdkmanage;

import com.microsoft.azure.keyvault.KeyVaultClient;
import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.Subscription;
import com.microsoft.azure.management.resources.Tenant;
import com.microsoft.azuretools.authmanage.SubscriptionManager;
import com.microsoft.azuretools.utils.Pair;

import java.io.IOException;
import java.util.List;

public interface AzureManager {
    public static enum Environment {
        GLOBAL,
        CHINA,
        GERMAN,
        US_GOVERNMENT
    }
    
    Azure getAzure(String sid) throws IOException;
    List<Subscription> getSubscriptions() throws IOException;
    List<Pair<Subscription, Tenant>> getSubscriptionsWithTenant() throws IOException;
    Settings getSettings();
    SubscriptionManager getSubscriptionManager();
    void drop() throws IOException;
//    public List<Tenant> getTenants() throws Throwable;
    KeyVaultClient getKeyVaultClient(String tid) throws Exception;
    String getCurrentUserId() throws IOException;
    String getAccessToken(String tid) throws IOException;
    String getManagementURI() throws IOException;
    String getStorageEndpointSuffix();
    String getScmSuffix();
    Environment getEnvironment();
    String getPortalUrl();
}
