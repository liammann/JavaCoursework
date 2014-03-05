<?php

class Inventory
{
	private $objects = array();

	public function __construct()
	{
		// meh
	}

	public function addObject($object)
	{
		array_push($this->objects, $object);
	}

	public function removeObject($index)
	{
		$ths->objects[$index];
	}
}