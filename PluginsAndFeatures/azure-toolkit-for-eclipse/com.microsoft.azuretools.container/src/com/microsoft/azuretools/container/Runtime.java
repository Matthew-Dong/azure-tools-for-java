/**
 * Copyright (c) Microsoft Corporation
 *
 * All rights reserved.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.microsoft.azuretools.container;

import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DefaultDockerClient.Builder;
import com.spotify.docker.client.exceptions.DockerException;

public class Runtime {
    private static final Runtime INSTANCE = new Runtime();
    private String runningContainerId = null;
    private Builder dockerBuilder = null;

    private Runtime() { }

    public static Runtime getInstance() {
        return INSTANCE;
    }

    public synchronized String getRunningContainerId() {
        return runningContainerId;
    }

    public synchronized void setRunningContainerId(String runningContainerId) {
        this.runningContainerId = runningContainerId;
    }

    public synchronized Builder getDockerBuilder() {
        return dockerBuilder;
    }

    public synchronized void setDockerBuilder(Builder builder) {
        this.dockerBuilder = builder;
    }

    /**
     * clean running container.
     */
    public synchronized void cleanRuningContainer() throws DockerException, InterruptedException {
        if (runningContainerId != null) {
            dockerBuilder.build().stopContainer(runningContainerId, Constant.TIMEOUT_STOP_CONTAINER);
            dockerBuilder.build().removeContainer(runningContainerId);
            runningContainerId = null;
        }
        return;
    }
}
