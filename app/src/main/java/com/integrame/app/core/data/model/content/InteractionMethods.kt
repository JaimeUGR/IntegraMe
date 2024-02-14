/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.core.data.model.content

import kotlinx.serialization.Serializable

/**
 * Representa los posibles métodos de interacción de los usuarios.
 *
 * Los métodos de interacción modifican la interfaz, integrando adaptaciones
 * específicas para el usuario correspondiente.
 */
@Serializable
enum class InteractionMethods {
    /**
     * Indica que no es necesaria ninguna adaptación adicional.
     */
    Default,

    /**
     * Indica que el usuario necesita / realizará recorridos automáticos
     * secuenciales.
     */
    Sequential,

    /**
     * Indica la necesidad de soportar navegación a través de tabulaciones
     * (por ejemplo teclado).
     */
    TabbedNavigation,

    /**
     * Indica adaptaciones considerando que el usuario utiliza lectores de pantalla.
     */
    Narrated,

    /**
     * Indica adaptaciones considerando que el usuario utiliza control por voz.
     */
    VoiceControl,

    /**
     * Indica adaptaciones para simplificar el diseño de la interfaz.
     */
    Simplified
}
