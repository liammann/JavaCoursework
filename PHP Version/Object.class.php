<?php

abstract class Object
{
	protected $name;
	protected $description;
	protected $state;

	public function __construct()
	{
		// meh
	}

	public function getObjectName()
	{
		return $this->name;
	}

	public function getObjectDescription()
	{
		return $this->description;
	}
}