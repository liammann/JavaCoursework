<?php

class FixedObject extends Object
{
	public function __construct($name, $description)
	{
		$this->name = $name;
		$this->description = $description;
		$this->state = 'fixed';
	}
}