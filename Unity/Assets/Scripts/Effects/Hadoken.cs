using UnityEngine;
using System.Collections;

public class Hadoken : MonoBehaviour {

	// Use this for initialization
	Rigidbody body;
	[HideInInspector]
	public Fighter caster;
	public int damage;
	Collider coll;

	public void cast(int direction){
		body = GetComponent<Rigidbody> ();
		transform.localScale = new Vector3 (direction, transform.localScale.y,
			transform.localScale.z);
		body.AddRelativeForce (new Vector3 (200*direction, 0, 0));
		Invoke ("hide", 3f);
	}

	void hide(){
		Destroy (gameObject);
	}

	void OnTriggerEnter(Collider coll){
		if(coll.gameObject.tag == "fighter"){
			Fighter other = coll.gameObject.GetComponent<Fighter> ();
			if (caster.fighterName != other.fighterName) {
				other.giveDamage (damage);
				Destroy (gameObject);
			}
		}
	}
}
