<?php

abstract class Object
{
	protected $name;
	protected $description;
	protected $state;

	public function __construct($name, $description, $state)
	{
		$this->name = $name;
		$this->description = $description;
		$this->state = $state;
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