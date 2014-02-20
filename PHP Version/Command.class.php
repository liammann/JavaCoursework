<?php

class Command
{
	private $commandWord;
	private $secondWord;

	public function __construct($firstWord, $secondWord)
	{
		$this->commandWord = $firstWord;
		$this->secondWord = $secondWord;
	}

	public function getCommandWord()
	{
		return $this->commandWord;
	}

	public function getSecondWord()
	{
		return $this->secondWord;
	}

	public function isUnknown()
	{
		return $this->commandWord === null;
	}

	public function hasSecondWord()
	{
		return isset($this->secondWord);
	}
}