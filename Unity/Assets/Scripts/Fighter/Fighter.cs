using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Fighter : MonoBehaviour {
	public enum PlayerTipe{
		AI,
		PLAYER
	}

	Animator animation;
	public Fighter oponent;
	public string fighterName;
	[HideInInspector]
	public Rigidbody body;
	[HideInInspector]
	public AudioSource source;
	public PlayerTipe type;
	public float life;
	float totalLife;
	public PlayerStates currentState;

	public Scrollbar lifeBar;

	void Start () {
		totalLife = life;
		lifeBar.size = 1;
		source = GetComponent<AudioSource> ();
		animation = GetComponent<Animator> ();
		body = GetComponent<Rigidbody> ();
	}

	void UpdateHumaInput(){
		if (Input.GetAxis ("Horizontal") > 0.1) {
			animation.SetBool ("WALK", true);
		} else {
			animation.SetBool ("WALK", false);
		}

		if (Input.GetAxis ("Horizontal") < -0.1) {
			animation.SetBool ("WALK_BACK", true);
		} else {
			animation.SetBool ("WALK_BACK", false);
		}

		if (Input.GetAxis ("Vertical") < -0.1) {
			animation.SetBool ("DUCK", true);
		} else {
			animation.SetBool ("DUCK", false);
		}

		if (Input.GetKeyDown(KeyCode.UpArrow)) {
			animation.SetTrigger("JUMP");
		} 

		if (Input.GetKeyDown(KeyCode.Space)) {
			animation.SetTrigger("HADOKEN");
		} 
		if (Input.GetKeyDown (KeyCode.K)) {
			animation.SetTrigger("KICK");
		}
		if(Input.GetKeyDown(KeyCode.P)){
			animation.SetTrigger("PUNCH");
		}
	}

	void Update () {
		if (!BannerManager.instance.startFight) {
			return;
		}
		if (type == PlayerTipe.PLAYER) {
			UpdateHumaInput ();		
		}else if(type == PlayerTipe.AI){
			UpdateAIInput ();
		}
	}

	void UpdateAIInput(){
		animation.SetFloat ("distanceToTarget", distanceToTarget ());
		animation.SetFloat ("targetHealth", oponent.health);
	}

	public float health{
		get{
			return life / totalLife;	
		}
	}

	public void giveDamage(int damage){
		if (!invulnerable) {
			life -= damage;
			animation.SetTrigger ("TAKE_HIT");
			lifeBar.size = health;
			if (life <= 0) {
				animation.SetTrigger ("DEAD");
			}
		}

	}

	public float distanceToTarget(){
		return Mathf.Abs (transform.position.x - oponent.transform.position.x);
	}

	public bool invulnerable{
		get{
			return currentState == PlayerStates.TAKE_HIT ||
			currentState == PlayerStates.TAKE_HIT_DEFEND ||
			currentState == PlayerStates.DEAD;
		}
	}

	public bool attacking{
		get{ 
			return currentState == PlayerStates.ATTACK;
		}
	}

	public void victory(){
		animation.SetTrigger ("CELEBRATE");
	}


}
