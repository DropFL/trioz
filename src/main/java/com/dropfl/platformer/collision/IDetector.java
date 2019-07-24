package com.dropfl.platformer.collision;

import javax.annotation.Nonnull;

interface IDetector
{
    boolean detect(@Nonnull BoundingBox self, @Nonnull BoundingBox target);
}