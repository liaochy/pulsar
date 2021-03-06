/**
 * Copyright 2016 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.pulsar.client.admin;

import java.util.List;

import com.yahoo.pulsar.client.admin.PulsarAdminException.ConflictException;
import com.yahoo.pulsar.client.admin.PulsarAdminException.NotAuthorizedException;
import com.yahoo.pulsar.client.admin.PulsarAdminException.NotFoundException;
import com.yahoo.pulsar.client.admin.PulsarAdminException.PreconditionFailedException;
import com.yahoo.pulsar.common.policies.data.PropertyAdmin;

/**
 * Admin interface for properties management
 */
public interface Properties {
    /**
     * Get the list of properties.
     * <p>
     * Get the list of all the properties.
     * <p>
     * Response Example:
     *
     * <pre>
     * <code>["my-property", "other-property", "third-property"]</code>
     * </pre>
     *
     * @return the list of Pulsar tenants properties
     * @throws NotAuthorizedException
     *             Don't have admin permission
     * @throws PulsarAdminException
     *             Unexpected error
     */
    List<String> getProperties() throws PulsarAdminException;

    /**
     * Get the config of the property.
     * <p>
     * Get the admin configuration for a given property.
     *
     * @param property
     *            Property name
     * @return the property configuration
     *
     * @throws NotAuthorizedException
     *             Don't have admin permission
     * @throws NotFoundException
     *             Property does not exist
     * @throws PulsarAdminException
     *             Unexpected error
     */
    PropertyAdmin getPropertyAdmin(String property) throws PulsarAdminException;

    /**
     * Create a new property.
     * <p>
     * Provisions a new property. This operation requires Pulsar super-user privileges.
     *
     * @param property
     *            Property name
     * @param config
     *            Config data
     *
     * @throws NotAuthorizedException
     *             Don't have admin permission
     * @throws ConflictException
     *             Property already exists
     * @throws PreconditionFailedException
     *             Property name is not valid
     * @throws PulsarAdminException
     *             Unexpected error
     */
    void createProperty(String property, PropertyAdmin config) throws PulsarAdminException;

    /**
     * Update the admins for a property.
     * <p>
     * This operation requires Pulsar super-user privileges.
     *
     * @param property
     *            Property name
     * @param config
     *            Config data
     *
     * @throws NotAuthorizedException
     *             Don't have admin permission
     * @throws NotFoundException
     *             Property does not exist
     * @throws PulsarAdminException
     *             Unexpected error
     */
    void updateProperty(String property, PropertyAdmin config) throws PulsarAdminException;

    /**
     * Delete an existing property.
     * <p>
     * Delete a property and all namespaces and destinations under it.
     *
     * @param property
     *            Property name
     *
     * @throws NotAuthorizedException
     *             Don't have admin permission
     * @throws NotFoundException
     *             The property does not exist
     * @throws ConflictException
     *             The property still has active namespaces
     * @throws PulsarAdminException
     *             Unexpected error
     */
    void deleteProperty(String property) throws PulsarAdminException;
}
