package com.dropfl.platformer.collision;

interface IDetector
{
    boolean detect(BoundingBox self, BoundingBox target);
}