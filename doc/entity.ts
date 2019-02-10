/*
    this file describes the format of entity objects.
    it is a part of EntityEvent notation.
    see also "events.ts".
*/

interface entity {
    texture: String;    // relative path to image
    x: Motion;          // horizontal motion description
    y: Motion;          // vertical motion description
    rotation?: Motion;  // rotational motion description
    isEnemy?: boolean;  // if this entity is an enemy
}

interface Motion {
    prerequisite?: string[];    // pre-compiled part of formula, like user-defined functions
    formula: string;            // actual formula that returns a number
}