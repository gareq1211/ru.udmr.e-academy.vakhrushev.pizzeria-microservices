package com.pizzeria.shared.client;

import com.pizzeria.shared.dto.KitchenTaskDto;

public interface KitchenServiceClient {
    void sendTaskToKitchen(KitchenTaskDto task);
}
