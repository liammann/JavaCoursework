<?php

class MovableObject extends Object
{
	public function __construct($name, $description)
	{
		$this->name = $name;
		$this->description = $description;
		$this->state = 'movable';
	}
}