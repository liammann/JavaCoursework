<?php

class GameData
{
	private static $locationHistory = array();

	public function __construct()
	{
		// meh
	}

	public function getCurrentLocation()
	{
		return end(static::$locationHistory);
	}

	public function setNewLocation($location = null, $backtrack = 0)
	{
		$historyCount = count(static::$locationHistory);

		if($backtrack === 0) {
			static::$locationHistory[$historyCount] = $location;
			return; // output what the new location has been set to? means changing game class (welcome() method)
		}

		static::$locationHistory[$historyCount] = static::$locationHistory[$historyCount-$backtrack-1];
		return $this->getCurrentLocation()->getLongDescription();
	}
}