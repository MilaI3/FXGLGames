/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2016 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.mario.control;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.ents.component.Required;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;

@Required(PhysicsComponent.class)
public class PhysicsLiftControl extends AbstractControl {

    private double spawnY;
    private boolean movingUp = true;

    private PositionComponent position;
    private PhysicsComponent physics;

    public PhysicsLiftControl(double spawnY) {
        this.spawnY = spawnY;
    }

    @Override
    public void onAdded(Entity entity) {
        position = entity.getComponentUnsafe(PositionComponent.class);
        physics = entity.getComponentUnsafe(PhysicsComponent.class);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        physics.setLinearVelocity(0, 60 * 60 * tpf * (movingUp ? - 1 : 1));

        if (position.getY() - spawnY > 90 && physics.getLinearVelocity().getY() > 0)
            movingUp = !movingUp;
        else if (position.getY() - spawnY < -90 && physics.getLinearVelocity().getY() < 0)
            movingUp = !movingUp;
    }
}
