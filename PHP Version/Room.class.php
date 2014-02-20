<?php

class Room
{
	private $description = '';
	private $exits = array();

	public function __construct($description)
	{
		$this->description = $description;
	}

	public function setExit($direction, Room $neighbour)
	{
		if(isset($this->exits[$direction])) {
			// already set
		}
		
		$this->exits[$direction] = $neighbour;
	}

	public function getShortDescription()
	{
		return $this->description;
	}

	public function getLongDescription()
	{
		return "You are {$this->description}\r\n{$this->getExitString()}";
	}

	public function getExitString()
	{
		$exits = 'Exits: ';
		$keys = array_keys($this->exits);

		foreach($keys as $exit) {
			$exits .= " {$exit}";
		}
		$exits .= PHP_EOL;

		return $exits;
	}

	public function getExit($direction)
	{
		return $this->exits[$direction];
	}
}