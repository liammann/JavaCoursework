<?php

class Location
{
	private $description = '';
	private $exits = array();
	private $locked;
	private $items = array();

	public function __construct($description)
	{
		$this->description = $description;
	}

	public function setExit($direction, Location $neighbour)
	{
		if(!isset($this->exits[$direction])) {
			$this->exits[$direction] = $neighbour;
		}
	}

	public function setItem($item)
	{
		array_push($this->items, $item);
	}

	public function getShortDescription()
	{
		return $this->description;
	}

	public function getLongDescription()
	{
		return "You are {$this->description}\r\n{$this->showExits()}";
	}

	public function getItems()
	{
		if(count($this->items) !== 0) {
			$answer = "This room has the folloring items:\r\n";

			foreach($this->items as $item) {
				$answer .= ' - '.$item.getDescription()."\r\n";
			}

			return $answer;
		}

		return 'There are no items in this room.';
	}

	public function showExits()
	{
		$exits = 'Exits:';
		$keys = array_keys($this->exits);

		foreach($keys as $exit) {
			$exits .= " {$exit}";
		}

		return $exits;
	}

	public function isValidExit($direction)
	{
		return isset($this->exits[$direction]);
	}

	public function getExit($direction)
	{
		return $this->exits[$direction];
	}

	public function setLock()
	{
		$this->locked = true;
	}

	public function isLocked()
	{
			return $this->locked();
	}

	public function unlock()
	{
		$this->locked = false;
	}
}