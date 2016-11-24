using UnityEngine;
using System.Collections;

public class BannerManager : MonoBehaviour {

	AudioSource source;
	public AudioClip roundClip;
	public AudioClip fightClip;
	public AudioClip backgroundMusic;

	public static BannerManager instance;
	public bool startFight;

	Animator animation;

	void Awake(){
		instance = this;
	}

	void Start () {
		animation = GetComponent<Animator> ();
		source = GetComponent<AudioSource> ();
	}

	public void roundSound(){
		SoundManager.playSound (source, roundClip);
	}

	public void fightSound(){
		SoundManager.playSound (source, fightClip);
	}

	public void playFight(){
		SoundManager.playSound (source, backgroundMusic,1,true);
		startFight = true;
		HudController.instance.CountDownStart ();
	}

	public void Player1Win(){
		animation.SetTrigger ("WIN");
	}

	public void Player2Win(){
		animation.SetTrigger ("LOSE");
	}
}
