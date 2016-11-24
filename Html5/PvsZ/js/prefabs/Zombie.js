Zombie = function(state, x, y, data) {
  Phaser.Sprite.call(this, state.game, x, y, data.asset);

  this.state = state;
  this.game = state.game;
  this.anchor.setTo(0.5);
  this.game.physics.arcade.enable(this);

  this.reset(x, y, data);
};

Zombie.prototype = Object.create(Phaser.Sprite.prototype);
Zombie.prototype.constructor = Zombie;

Zombie.prototype.reset = function(x, y, data) {
  Phaser.Sprite.prototype.reset.call(this, x, y, data.health);
  this.loadTexture(data.asset);
  this.animationName = null;
  if(data.animationFrames) {
    this.animationName = data.asset + 'Anim';
    this.animations.add(this.animationName, data.animationFrames, 4, true);
    this.play(this.animationName);
  }
  this.attack = data.attack;
  this.defaultVelocity = data.velocity;
  this.body.velocity.x = data.velocity;
};

Zombie.prototype.damage = function(amount) {
  Phaser.Sprite.prototype.damage.call(this, amount);

  var emitter = this.game.add.emitter(this.x, this.y, 50);
  emitter.makeParticles('bloodParticle');
  emitter.minParticleSpeed.setTo(-100, -100);
  emitter.maxParticleSpeed.setTo(100, 100);
  emitter.gravity = 300;
  emitter.start(true, 200, null, 100);
  if(this.health <= 0) {
    var corpse = this.game.add.sprite(this.x, this.bottom, 'deadZombie');
    corpse.anchor.setTo(0.5, 1);
  }
};