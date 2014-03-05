<?php

class Character
{
	// private $inventory;
	private $name;
	private $health;
	private $currentLocation;
	private $strength;

	public function __construct($name, $strength, $health)
	{
		// $this->inventory = new Inventory();
		$this->name = $name;
		$this->strength = $strength;
		$this->health = $health;
	}

	private function getHealth()
	{
		return $this->health;
	}

	private function setHealth($health)
	{
		$this->health = $health;
	}

/******* [START] Don't need *******/
	private function setStrength($strength)
	{
		$this->strength = $strength;
	}
/******* [END] Don't need *******/
	
	public function setLocation($current)
	{
		$this->currentLocation = $current;
	}
}