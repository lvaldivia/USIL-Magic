using UnityEngine;
using System.Collections;

public class StageManager : MonoBehaviour {

	AudioSource source;
	public AudioClip roundClip;
	public AudioClip fightClip;
	public AudioClip backgroundMusic;

	void Start () {
		source = GetComponent<AudioSource> ();
		SoundManager.playSound (source, roundClip);
		Invoke ("playFight", 1.3f);
	}

	void playFight(){
		SoundManager.playSound (source, fightClip);
		Invoke ("playBackground", 0.4f);
	}

	void playBackground(){
		//SoundManager.playSound (source, backgroundMusic,1,true);
	}

}
