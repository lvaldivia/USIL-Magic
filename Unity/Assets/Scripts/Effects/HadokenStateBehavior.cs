using UnityEngine;
using System.Collections;

public class HadokenStateBehavior : FighterStateBehavior {

	override public void OnStateEnter(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
		base.OnStateEnter (animator, stateInfo, layerIndex);
		float fighterX = fighter.transform.position.x;
		GameObject go = Object.Instantiate(Resources.Load("Hadoken"),new Vector3(fighterX,1,fighter.transform.position.z),Quaternion.Euler(0,0,0)) as GameObject;
		Hadoken hado = go.gameObject.GetComponent<Hadoken> ();
		hado.caster = fighter;
		int direction = (int)fighter.transform.eulerAngles.y == 90 ? 1 : -1;
		hado.cast (direction);
	}

	 // OnStateEnter is called when a transition starts and the state machine starts to evaluate this state
	//override public void OnStateEnter(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateUpdate is called on each Update frame between OnStateEnter and OnStateExit callbacks
	//override public void OnStateUpdate(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateExit is called when a transition ends and the state machine finishes evaluating this state
	//override public void OnStateExit(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateMove is called right after Animator.OnAnimatorMove(). Code that processes and affects root motion should be implemented here
	//override public void OnStateMove(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}

	// OnStateIK is called right after Animator.OnAnimatorIK(). Code that sets up animation IK (inverse kinematics) should be implemented here.
	//override public void OnStateIK(Animator animator, AnimatorStateInfo stateInfo, int layerIndex) {
	//
	//}
}
