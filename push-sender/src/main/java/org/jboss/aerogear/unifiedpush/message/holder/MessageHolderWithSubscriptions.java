/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
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
package org.jboss.aerogear.unifiedpush.message.holder;

import org.jboss.aerogear.unifiedpush.api.FlatPushMessageInformation;
import org.jboss.aerogear.unifiedpush.api.Variant;
import org.jboss.aerogear.unifiedpush.message.UnifiedPushMessage;
import java.io.Serializable;
import java.util.Collection;
import org.jboss.aerogear.unifiedpush.dto.Subscription;

/**
 * Holds push message details together with information what specific variant and which device tokens should be notifications sent to.
 *
 * Holder is used as a payload in messaging subsystem.
 */
public class MessageHolderWithSubscriptions extends AbstractMessageHolder {
    private static final long serialVersionUID = -7955411139315335655L;
    private int serialId;
    private Variant variant;
    private Collection<Subscription> subscriptions;
    
    public MessageHolderWithSubscriptions(FlatPushMessageInformation pushMessageInformation, UnifiedPushMessage unifiedPushMessage, Variant variant, Collection<Subscription> subscriptions, int serialId) {
        super(pushMessageInformation, unifiedPushMessage);
        if (!(subscriptions instanceof Serializable)) {
            throw new IllegalArgumentException("deviceTokens must be a serializable collection");
        }
        this.variant = variant;        
        this.serialId = serialId;
        this.subscriptions = subscriptions;
    }
    
    public Variant getVariant() {
        return variant;
    }
    
    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }
    
     public int getSerialId() {
        return serialId;
    }
}